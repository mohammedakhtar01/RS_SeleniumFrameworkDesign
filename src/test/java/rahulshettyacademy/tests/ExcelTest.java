package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.data.ExcelReader;

public class ExcelTest {

	@Test(dataProvider = "customerDetails")
	public void Test_1(String fName, String lName, String city, int age, boolean isAdult) {

		System.out.println(fName+" : "+ lName+" : "+ city+" : "+ age +" : "+isAdult );

	}

	@DataProvider(name = "customerDetails")
	public Object[][] getData() throws Exception {

//		Object[][] data = { { "Mohd","Akhtar","Delhi",30,true }, 
//							{ "Suhana","Parveen","Sln",25,true },
//							{ "Zohan","Ahmed","Mumbai",2,false }
//		};
		
		ExcelReader reader = new ExcelReader("TestData", "CustomerData");
		return reader.getAllData();
		//return reader.getDataByTestName("TC_03");
	}

}
