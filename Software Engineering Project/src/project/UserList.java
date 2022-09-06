package project;
public class UserList {

	Node head;

	public class Node {
		int data;
		public Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	public void printUsersList() {
		Node tnode = head;
		while (tnode != null) {
			System.out.print(tnode.data + " ");
			System.out.println();
			tnode = tnode.next;
		}
	}

	public void Useremove(int id) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}

		Node temp = head, prev = null;

		if (temp != null && temp.data == id) {
			head = temp.next;

			return;

		}

		while (temp != null && temp.data != id) {
			prev = temp;
			temp = temp.next;

		}

		if (temp == null)

			return;

		prev.next = temp.next;

	}

	public void useradd(int new_data) throws InvalidInputException

	{
		if (new_data > 9999999 || new_data < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of " + new_data);
		}
		Node new_node = new Node(new_data);
		new_node.next = head;
		head = new_node;
		System.out.println("User " + new_data + " successfully added");
	}

	public String UserCheck(Node head, int id) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of " + id);
		}
		Node current = head;
		while (current != null) {
			if (current.data == id)

				current = current.next;
			return "user " + id + " exist";

		}
		return "User not found";

	}

	public int UserCount() {
		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	public boolean sysaccess(Node head, int id) {
		Node current = head;
		while (current != null) {
			if (current.data == id)
				return true;
			current = current.next;
		}
		return false;
	}

	public void attendance(int id, int[] attlist) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		boolean attended = false;
		for (int i = 0; i < attlist.length; i++) {
			if (attlist[i] == id) {
				attended = true;
			}
		}
		if (attended) {
			System.out.println("attended");
		} else {
			System.out.println("absent");
		}
	}

	public int workhours(int id, int hoursworked, int overallhours) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		int workhours = overallhours - hoursworked;
		return Math.abs(workhours);
	}

	public void absStudentRecord(int id, int absence, int allowedabsences) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		if (absence == allowedabsences) {
			System.out.println("the limit of absences reached");
		} else if (absence > allowedabsences) {
			System.out.println("exceeded absences limit");
		} else {
			System.out.println("still below limit");
		}

	}

	public void acceptedOrDec(int id, int[] acclist) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		boolean accepted = false;
		for (int i = 0; i <= acclist.length; i++) {
			if (acclist[i] == id) {
				accepted = true;
			}
		}
		if (accepted) {
			System.out.println("The application was accepted for this student");
		} else {
			System.out.println("The application was rejected for this student");
		}
	}

	public void work(int id, int tuition) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}

		tuition = (int) (tuition * 0.80);

		System.out.println("The new tuition of the following student(" + id + ") is " + tuition +"$");
	}

	public void bankLoans(int id, int tuition) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}

		tuition = (int) (tuition * 0.70);

		System.out.println("The new tuition of the following student(" + id + ") is " + tuition+"$");
	}

	public void study(int id, int tuition) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		tuition = (int) (tuition * 0.60);

		System.out.println("The new tuition of the following student(" + id + ") is " + tuition +"$");
	}

	public void checkDoc(int id, int[] doclist) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		boolean corr = false;
		for (int i = 0; i <= doclist.length; i++) {
			if (doclist[i] == id) {
				corr = true;
			}
		}
		if (corr) {
			System.out.println("Documents are correct for this student");
		} else {
			System.out.println("Documents are incorrect for this student");
		}
	}

	public void probation(int id, int[] problist) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		boolean prob = false;
		for (int i = 0; i <= problist.length; i++) {
			if (problist[i] == id) {
				prob = true;
			}
		}
		if (prob) {
			System.out.println("This student can not have a financial aid due to a probation");
		} else {
			System.out.println("This student does not have a probation and can have a financial aid");
		}
	}

	public void incompleteInfo(int id, int[] incomplist) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {

			throw new InvalidInputException("please enter a valid 7-digit id number instead of " + id);

		}
		boolean incomp = false;
		for (int i = 0; i <= incomplist.length; i++) {
			if (incomplist[i] == id) {
				incomp = true;
			}
		}
		if (incomp) {
			System.out.println("This student can not have a financial aid due to incomplete information");
		} else {
			System.out.println("This student has complete information");
		}
	}

}
