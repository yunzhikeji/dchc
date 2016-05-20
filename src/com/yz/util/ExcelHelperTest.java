package com.yz.util;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.yz.model.Person;


/**
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 */
public class ExcelHelperTest {

	@Test
	public void test() {
		String path = "D://test//template.xls";
		System.out.println(new File(path).exists());
		List<Person> list = null;
		try {
			list = ExcelHelper.exportListFromExcel(new File(path),FilenameUtils
					.getExtension(new File(path).getName()), 0);
			System.out.println(list.size());
			
			for(Person person:list)
			{
				System.out.println(person.getBirthday()+" "+person.getTelphone()+" "+person.getName() );
			}
			
		} catch (IOException e) {
			fail();
		}

	}
}