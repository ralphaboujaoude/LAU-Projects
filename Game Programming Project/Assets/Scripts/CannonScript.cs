using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class CannonScript : MonoBehaviour
{
    public GameObject camera;

    public GameObject bullet;
    public GameObject rotator, cannonbarrel;
    public int rotationSpeed, shootingForce;
    public GameObject spawnPosition;

    public void Update()
    {
        if (Input.GetButtonDown("Fire1"))
        {
            Debug.Log(gameObject.name +  " - Fire Cannonball");
            GameObject b = Instantiate(bullet, spawnPosition.transform.position, spawnPosition.transform.rotation);

            b.GetComponent<Rigidbody>().AddForce(cannonbarrel.transform.forward * shootingForce);
        }

        rotator.transform.Rotate(0, rotationSpeed * Input.GetAxis("Horizontal") * Time.deltaTime,0);
        cannonbarrel.transform.Rotate(-rotationSpeed * Input.GetAxis("Vertical") * Time.deltaTime, 0,0);
    }
}
