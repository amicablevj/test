package pageUtility;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TaskPage {

	WebDriver driver;

	public TaskPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//find and define the elements present on the app and are needed for performing tests.
	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']")
	public WebElement inputField;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::li[contains(@class,'done-false')]") })
	public List<WebElement> toDoTasks;

	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::li[contains(@class,'done-false')][1]/div/input")
	public WebElement firstToDoTask;

	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::span[@class='number']/span")
	public WebElement toDoTaskCount;
	
	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::li[contains(@class,'done-true')]/div/input")
	public WebElement firstCompletedTask;
	
	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::li[contains(@class,'done-true')]")
	public WebElement completedTasks;

	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::span[@class='number-done']/span")
	public WebElement completedTaskCount;

	@FindBy(how = How.XPATH, using = "//input[@id='new-todo']/following::span[contains(@class,'todo-clear')]/a")
	public WebElement clearCompletedTask;

}
