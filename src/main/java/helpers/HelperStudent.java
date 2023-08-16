package helpers;

import models.StudentDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface HelperStudent extends HelperBase{

    default void selectItemForms(){
        click(By.xpath("//div[@class='category-cards']/div[2]"));
    }

    default void selectPracticeForm(){
        click(By.xpath("//span[.='Practice Form']"));
    }

    default void fillForm(StudentDTO studentDTO){
        type(By.id("firstName"), studentDTO.getFirstName());
        type(By.id("lastName"), studentDTO.getLastName());
        type(By.id("userEmail"), studentDTO.getEmail());
        selectGender(studentDTO.getGender());
        type(By.id("userNumber"), studentDTO.getPhone());
        dateOfBirthDays(studentDTO.getBirthday());
  //      type(By.id("dateOfBirthInput"), studentDTO.getBirthday());
        addSubjects(studentDTO.getSubjects());
        selectHobby(studentDTO.getHobbies());
        type(By.id("currentAddress"), studentDTO.getAddress());
        selectState(studentDTO.getState());
        selectCity(studentDTO.getCity());
    }

    default void dateOfBirthDays(String birthday){
        String[] birthdays = birthday.split(" ");
        int x = 2023 - Integer.parseInt(birthdays[2]);
        int y = Integer.parseInt(birthdays[1]);
        int i = x * 11 + y;
        click(By.xpath("//input[@id='dateOfBirthInput']"));
        while(i != 0){
        click(By.xpath("//button[normalize-space()='Previous Month']"));
        }
        String value = birthdays[0] ;
        StringBuilder builder = new StringBuilder();
        builder.append(value);
        String locator = builder.toString();
        click(By.xpath(locator));
       // click(By.xpath("//select[@class='react-datepicker__month-select']"));
////div[@aria-label='Choose Monday, July 17th, 2023']

    };

    default void selectGender(String gender){
        if(gender.equals("Male")){
            click(By.xpath("//label[@for='gender-radio-1']"));
        } else if(gender.equals("Female")){
            click(By.xpath("//label[@for='gender-radio-2']"));
        } else {
            click(By.xpath("//label[@for='gender-radio-3']"));
        }
    }

    default void addSubjects(String subject){
        String[] subjects = subject.split(",");
        String locator = "subjectsInput";
        click(By.id(locator));
        for(String subj : subjects){
            wd.findElement(By.id(locator)).sendKeys(subj);
            click(By.id("react-select-2-option-0"));
        }
    }

    default void selectHobby(String hobby){
        String[] hobbies = hobby.split(",");
        for (String hobb : hobbies){
            switch (hobb){
                case "Sports":
                    click(By.xpath("//label[@for='hobbies-checkbox-1']"));
                    break;
                case "Reading":
                    click(By.xpath("//label[@for='hobbies-checkbox-2']"));
                    break;
                case "Music":
                    click(By.xpath("//label[@for='hobbies-checkbox-3']"));
                    break;
            }

        }
    }

    default void selectState(String state){
        WebElement element = wd.findElement(By.id("react-select-3-input"));
        element.sendKeys(state);
        element.sendKeys(Keys.ENTER);
    }
    default void selectCity(String city){
        WebElement element = wd.findElement(By.id("react-select-4-input"));
        element.sendKeys(city);
        element.sendKeys(Keys.ENTER);
    }

    default void submitForm(){
        click(By.id("submit"));
    }
}
