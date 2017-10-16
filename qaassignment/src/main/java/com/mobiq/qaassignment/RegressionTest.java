package com.mobiq.qaassignment;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageUtility.TaskPage;
import utility.BrowserFactory;
import utility.ExcelUtility;
public class RegressionTest {

	WebDriver driver;
	String baseUrl = "http://cburgdorf.github.io/angular-todo-app/#/main";
	int openTaskCount = 0;
	TaskPage home;
	int toDoInputSource;
	SoftAssert sa = new SoftAssert();

	@BeforeSuite
	public void setUp() {
		
		//Initiate the browser utilities
		BrowserFactory browser = new BrowserFactory();
		driver = browser.chromeBrowser(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//initiate the page utilities
		home = new TaskPage(driver);
	}

	
	
	@Test(priority = 1, description = "U1: Add Task to the ToDo List ", dataProvider = "toDoTasks")
	public void AddToDoTask(String Tasks) throws Exception {
		
		// Add Tasks to ToDoList from the testData
		if (Tasks != null) {
			home.inputField.sendKeys(Tasks);
			home.inputField.sendKeys(Keys.ENTER);
		}
	}
	
	@Test(priority = 2, description = "Verify the number of tasks added equals the number of taks from source")
	public void CountToDoTask() {
		
		// Assert the count on source equals the count of ToDoTasks in the list
		sa.assertEquals(home.toDoTasks.size(), toDoInputSource, "SourceToDoTasksCount not equals the ToDoTasks");
		sa.assertAll();
	}
	
	@Test(priority = 3, description = "U4: Complete the first ToDo task ")
	public void MarkTaskCompleted() throws Exception {
		
		//click on checkbox for the firstToDo task
		home.firstToDoTask.click();
		
		// Assert the count of remaining ToDo Tasks shown on app equals the number of ToDo Tasks in the list
		sa.assertEquals(home.toDoTasks.size(), toDoInputSource-1, "Task is not marked as conpleted");
		}
	
	@Test(priority = 4, description = "U6 : Verify the number of remaining tasks to be done")
	public void ValidateRemainingTasksCount() {
		
		// Assert the count of remaining ToDo Tasks shown on app equals the number of ToDo Tasks in the list
		sa.assertNotEquals(home.toDoTaskCount.getText(), home.toDoTasks.size(), "Count of remaining tasks not matching the value shown on app");
		sa.assertAll();
	}
	
	@Test(priority = 5, description = "U4-1 : Verify the number of completed tasks")
	public void ValidateCompletedTasksCount() {
		
		// Assert the count of completed Tasks shown on app equals the number of completed Tasks in the list
		sa.assertNotEquals(home.completedTasks.getSize(), home.completedTaskCount.getText(), "Count of Completed tasks not matching the value shown on app");
		sa.assertAll();
	}
	
	@Test(priority = 6, description = "U3 : Clear completed tasks")
	public void DeleteCompletedTasks() {
		
		//click on checkbox for the firstToDo task
		home.firstToDoTask.click();
		
		// click on clear Completed Tasks href
		home.clearCompletedTask.click();
		
		// Assert the count of ToDoTasks shown on app is reduced by 1 the count of initial Inputsource
		sa.assertNotEquals(home.toDoTaskCount.getText(),toDoInputSource-2 , "Count of remaining tasks not matching the value shown on app");
		sa.assertAll();
	}

	@DataProvider(name = "toDoTasks")
	public Object[][] toDotasks() throws Exception {
		Object[][] toDoTasksArray = ExcelUtility.getDataArray("src\\main\\java\\TestData\\TestData.xls", 0);
		
		//get the number of rows from the sourcefile
		toDoInputSource = (toDoTasksArray.length) - 1;
		return toDoTasksArray;
	}

	@AfterSuite
	public void teardown() {
		//quits the active sessions of the browser initiated through this automated test iteration
		driver.quit();
	}

}
