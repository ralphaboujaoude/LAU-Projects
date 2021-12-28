package project;

public class FA_Budget {
int expenses;
int income;
int budget;


public FA_Budget( int expenses, int income, int budget) {
	this.budget=budget;
	this.expenses = expenses;
	this.income = income;
}

public FA_Budget( int  budget) {
	this.budget=budget;
	
}

public int getExpenses() {
	return expenses;
}


public void setExpenses(int expenses) {
	this.expenses = expenses;
	System.out.println("The current Expenses is" + expenses);
}


public int getIncome() {
	return income;
}


public void setIncome(int income) {
	this.income = income;
	System.out.println("The current Income is" + income);
}

int calcBudget (int expenses, int income) {
	int x=income - expenses;
	
	System.out.println("The current budget is:"+x);
	return x;
}

public int getBudget() {
	return budget;
}


public void setBudget(int budget) {
	this.budget = budget;
	System.out.println("The current budget is " + budget);
}



public String toString() {
	return "FA_Budget [expenses=" + expenses + ", income=" + income + ", budget=" + budget + "]";
}


}
