package project;

import project.UserList.Node;

public class StaffList {

	Node head;

	public class Node {
		int data;
		public Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	public void printStaffList() {
		Node tnode = head;
		while (tnode != null) {
			System.out.print(tnode.data + " ");
			System.out.println();
			tnode = tnode.next;
		}
	}

	public void StaffRemove(int id) throws InvalidInputException {
	  if( id > 9999999 || id < 1000000) {
		
			throw new InvalidInputException("please enter a valid 7-digit id number instead of "+id);

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

	public void StaffAdd(int new_data) throws InvalidInputException

	{
		if (new_data > 9999999 || new_data < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of "+new_data);
		}
		Node new_node = new Node(new_data);
		new_node.next = head;
		head = new_node;
	}

	public String StaffCheck(Node head, int id) throws InvalidInputException {
		if (id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of "+id);
		}
		Node current = head;
		while (current != null) {
			if (current.data == id)

				current = current.next;
			return "staff exist";

		}
		return "Staff not found";

	}

	public int StaffCount() {
		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

}
