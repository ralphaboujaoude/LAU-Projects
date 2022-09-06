#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <math.h>
#include <mpi.h>
#pragma warning (disable : 4996)
#define		X_RESN	15000    // setting default: x resolution 
#define		Y_RESN	10000   // setting default: y resolution 
#define     M_MAX   1048576 // setting  Max iterations for Mandelbrot 


// BITMAP header
#pragma pack(push, 1)
typedef struct
{
    uint16_t type;
    uint32_t size;
    uint16_t reserved1, reserved2;
    uint32_t offset;
} HEADER;
#pragma pack(pop)


// Below is the structure that holds the information of BitMap
#pragma pack(push, 1)
typedef struct
{
    uint32_t size;
    int32_t  width, height;
    uint16_t planes;
    uint16_t bits;
    uint32_t compression;
    uint32_t isize;
    int32_t  xres, yres;
    uint32_t colors;
    uint32_t impcolors;
} IHEADER;
#pragma pack(pop)
#pragma pack(push, 1)

// Structure to hold R-G-B values Pixel 
//Name of Structure is PIXEL
typedef struct Pixel
{
    uint8_t b, g, r;
} Pixel;

#pragma pack(pop)

// Structure to hold info on a bitmap
// Structure to hold header parameters 
//Name of Structure is BMP
#pragma pack(push, 1)
typedef struct
{
    HEADER header;
    IHEADER iheader;
    Pixel** array;
} BMP;
#pragma pack(pop)

//Function to verify whether our BMP-File is loaded into the memory of code or not.
//This function returs exit(1) error msg if file is not successfully loaded
int get_bmp_header(FILE* fp, BMP* img)
{
    //Case 1: If image file is unable to open
    if (sizeof((*img).header) != fread(&(*img).header, 1, sizeof((*img).header), fp))
    {
        perror("Unable to read file");
        exit(1);
    }
    //Case 2: If image file is unable to open
    if (sizeof((*img).iheader) != fread(&(*img).iheader, 1, sizeof((*img).iheader), fp))
    {
        perror("Unable to read file");
        exit(1);
    }
    //Case 3: If provided file is not an image
    if ((*img).header.type != 0x4D42)
    {
        printf("Error: Not a valid bitmap file.\n");
        exit(1);
    }
    return 0;
}

//This function basically prints the parameters of image file that we are using
int print_bmp_attr(BMP* bmp)
{
    //We are extracting Image boundaries
    int linebits = (*bmp).iheader.width * (*bmp).iheader.bits;
    int linebytes = ((linebits + 31) / 32) * 4;
    printf("Below are the parameters of our custom BMP-File");
    printf("File: \n\
        Linebytes: %d\n\
        Type: %X\n\
        Size: %d\n\
        Offset: %d\n\
        Size: %d\n\
        Res1: %d\n\
        Res2: %d\n\
        Width: %d\n\
        Height: %d\n\
        Image Size: %u\n\
        Xres: %u\n\
        Yres: %u\n\
        Planes: %u\n\
        Bits/Pixel: %u\n\
        Compression: %u\n\
        Number of colors: %u\n\
        Important colors: %u\n", linebytes, (*bmp).header.type, (*bmp).header.size, (*bmp).header.offset, (*bmp).iheader.size, (*bmp).header.reserved1, (*bmp).header.reserved2, (*bmp).iheader.width, (*bmp).iheader.height, (*bmp).iheader.isize,
        (*bmp).iheader.xres, (*bmp).iheader.yres, (*bmp).iheader.planes, (*bmp).iheader.bits, (*bmp).iheader.compression, (*bmp).iheader.colors, (*bmp).iheader.impcolors);
    return 0;
}

//Structure for complex type BMP
typedef struct complextype
{
    float real, imag;
} Compl;
//Name of Structure for complexType is Compl

//We are creating a enum named TAGS. 
//It has Data_Tag, Tera_Tag, Result_Tag
typedef enum { DATA_TAG, TERM_TAG, RESULT_TAG } Tags;

Pixel cal_pixel(Compl c, int size, int rank)
{
    Pixel result;
    uint64_t count;
    uint64_t colors = 0xFFFFFF / M_MAX;
    int rcolors = 0xFF / size;
    Compl z;
    float temp, lengthsq;
    z.real = 0; z.imag = 0;
    count = 0;
    do
    {
        //We are refering to Pixel structure to store the values
        temp = z.real * z.real - z.imag * z.imag + c.real;
        z.imag = 2 * z.real * z.imag + c.imag;
        z.real = temp;
        lengthsq = z.real * z.real + z.imag * z.imag;
        //We are incrementing count for ever iteration
        count++;
    } while ((lengthsq < 4.0) && (count < M_MAX));
    //Result.b refers to HEX 
    result.b = rank * count * colors & 0xFF;
    result.g = rank * count * colors;
    //result.r = z.real 
    //result.r = rank*rcolors & 0xFF0000;

    //We are storing modulus of rank.color and count to result.r
    result.r = rank * colors % count;
    //Return the result obtained
    return result;
}
void worker(int linebytes)
{
    //We are dynamically allocating memory to color array that is of structure PIXEL
    Pixel* colors = malloc(linebytes);
    MPI_Status status;
    int row, x, rank, size;

    //Base funtions of MPI.h Header file
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Recv(&row, 1, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);

    //We are creating an instance of Compl structure 
    Compl c;
    //We are creating an instance of Pixel structure 
    Pixel xp;
    while (status.MPI_TAG != TERM_TAG)
    {
        c.imag = ((float)row - 5500) / 3500.0;
        for (x = 0; x < linebytes / 3; x++)
        {
            c.real = ((float)x - 8000) / 3500.0;
            //We are storing values of pixel to colors[x]
            colors[x] = cal_pixel(c, rank, size);
        }
        MPI_Send(&colors[0], linebytes, MPI_BYTE, 0, row, MPI_COMM_WORLD);
        //   fprintf(stderr,"Worker: %d Row: %d\n",rank,row);
        MPI_Recv(&row, 1, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);

    }
}

//This is the main function
int main(int argc, char** argv)
{
    int rank, size;
    //Mpi.h is not confugured
    if (MPI_Init(&argc, &argv) != MPI_SUCCESS)
    {
        perror("Unable to initialize MPI\n");
        exit(1);
    }
    //MPI Rank & Size
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    BMP bmp;
    //We are setting values of BMP Structure 
    bmp.header.type = 0x4D42;
    bmp.iheader.xres = bmp.iheader.yres = 3780;
    bmp.header.reserved1 = bmp.header.reserved2 = 0;
    bmp.iheader.size = 40;
    bmp.iheader.compression = 0;
    bmp.iheader.width = X_RESN;
    bmp.iheader.bits = 24;
    bmp.iheader.colors = 0;
    bmp.iheader.impcolors = 0;
    bmp.iheader.height = Y_RESN;
    
    //Initialising FILE pointer fp;
    FILE* fp = 0;

    //If new file is not accessed
    if ((fp = fopen("/Users/RalphAj/sample_640Ã_426.bmp", "r+")) == NULL)
    {
        perror("Cannot create file");
        exit(1);
    }


    //  get_bmp_header(fp,&bmp);
    int linebits = bmp.iheader.width * bmp.iheader.bits;
    int linebytes = ((linebits + 31) / 32) * 4;
    if (rank != 0)
        worker(linebytes);
    else
    {
        //block
        bmp.iheader.planes = 1;
        bmp.iheader.isize = linebytes * bmp.iheader.height;
        bmp.header.offset = 54;
        bmp.header.size = bmp.header.offset + bmp.iheader.isize;
        //block
        print_bmp_attr(&bmp);

        fseek(fp, 0, SEEK_SET);
        //We are writing parameters to our created file 
        fwrite(&bmp.header, 1, sizeof(bmp.header), fp);
        fwrite(&bmp.iheader, 1, sizeof(bmp.iheader), fp);
        int k, count, row, column, byte;

        //we are creating array of pixel structure
        Pixel* current;
        //We are dynamically allocating memory to newImg array of Pixel structure
        Pixel** newimg = malloc(sizeof(Pixel*) * bmp.iheader.height);

        //Traversing bmp.header 
        for (row = bmp.iheader.height - 1; row >= 0; row--)
        {
            //Dynamically allocating memory to current
            current = (Pixel*)malloc(linebytes);
            column = 0;
            for (byte = 0; byte < linebytes; byte += 3)
            {
                //We are reading pixel info for each iteration
                fread(&current[column++], 1, sizeof(Pixel), fp);
            }
            newimg[row] = current;
            fseek(fp, bmp.header.offset + (bmp.iheader.height - 1 - row) * linebytes, SEEK_SET);
        }
        fclose(fp);
        /* Mandlebrot variables */

        bmp.array = newimg;
        //We are creating color array of Pixel structure
        Pixel* colors;
        count = 0;
        row = 0;
        for (k = 1; k < size; k++)
        {
            //Sending and then incrementing count and row by one.
            MPI_Send(&row, 1, MPI_INT, k, DATA_TAG, MPI_COMM_WORLD);
            count++;
            row++;
        }
        // memset(colors,0,linebytes);
        double start, end;
        MPI_Status status;
        start = MPI_Wtime();
        do
        {

            colors = (Pixel*)malloc(linebytes * sizeof(Pixel));
            MPI_Recv(&colors[0], linebytes, MPI_BYTE, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
            end = MPI_Wtime();
            fprintf(stderr, "[%d] Recv'd row: %d from %d.  Count = %d, row = %d %f s/row %f left\n",
                rank, status.MPI_TAG, status.MPI_SOURCE, count, row, (end - start) / (double)row, (end - start) / (double)row * (bmp.iheader.height - row));
            count--;
            if (row < bmp.iheader.height)
            {
                MPI_Send(&row, 1, MPI_INT, status.MPI_SOURCE, DATA_TAG, MPI_COMM_WORLD);
                row++;
                count++;
            }
            else
                MPI_Send(&k, 1, MPI_INT, status.MPI_SOURCE, TERM_TAG, MPI_COMM_WORLD);
            newimg[status.MPI_TAG] = colors;
        } while (count > 0);


        /* Program Finished */
        //We are printing info of each pixel.
        fprintf(stderr, "Received %d rows\n", row);
        if ((fp = fopen("/Users/RalphAj/sample_640Ã_426.bmp", "r+")) == NULL)
        {
            perror("Cannot create file");
            exit(1);
        }
        if (fseek(fp, bmp.header.offset, SEEK_SET) != 0)
            perror("seek");

        for (row = bmp.iheader.height - 1; row >= 0; row--)
        {
            if (fwrite(&newimg[row][0], 1, linebytes, fp) == 0)
                perror("fwrite");
        }
        fclose(fp);
    }
    MPI_Barrier(MPI_COMM_WORLD);

    //Last stage of MPI
    MPI_Finalize();
    return 0;
}
