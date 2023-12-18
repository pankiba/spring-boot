package com.pankiba.restfulwebservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.pankiba.restfulwebservices.domain.BusinessUnit;
import com.pankiba.restfulwebservices.domain.Employee;
import com.pankiba.restfulwebservices.repository.BusinessUnitRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataLoader implements CommandLineRunner, Ordered {

	@Autowired
	private BusinessUnitRepository businessUnitRepository;

	@Override
	public void run(String... args) throws Exception {

		log.info(" Running Data Loader ");

		BusinessUnit businessUnitOne = new BusinessUnit("ADM");

		businessUnitOne.addEmployee(new Employee(677509L, "John", "Rambo", "M", "john.rambo@users.noreply.github.com",
				parseDate("3/29/1987"), parseDate("11/24/2003"), 168251L));

		businessUnitOne.addEmployee(new Employee(940761L, "John", "McLane", "M", "john.mclane@users.noreply.github.com",
				parseDate("7/31/1970"), parseDate("7/27/2008"), 51063L));
		businessUnitOne.addEmployee(new Employee(499687L, "Ethan", "Hunt", "M", "ethan.hunt@yahoo.com",
				parseDate("9/27/1982"), parseDate("7/22/2005"), 72305L));
		businessUnitOne.addEmployee(new Employee(539712L, "Jery", "Maguire", "M", "jery.maguire@yahoo.com",
				parseDate("6/9/1959"), parseDate("4/27/1994"), 121587L));

		businessUnitRepository.save(businessUnitOne);

		BusinessUnit businessUnitTwo = new BusinessUnit("TSL");

		businessUnitTwo.addEmployee(new Employee(408351L, "Cersei", "Lanister", "F",
				"cersei.lanister@users.noreply.github.com", parseDate("12/4/1977"), parseDate("4/16/1999"), 180294L));
		businessUnitTwo.addEmployee(
				new Employee(333476L, "Daenerys", "Targaryen", "F", "daenerys.targaryen@users.noreply.github.com",
						parseDate("12/1/1967"), parseDate("12/22/1991"), 109394L));
		businessUnitTwo.addEmployee(new Employee(528673L, "Brann", "Stark", "M", "brann.stark@users.noreply.github.com",
				parseDate("3/9/1959"), parseDate("6/26/1995"), 145235L));

		businessUnitRepository.save(businessUnitTwo);

		BusinessUnit businessUnitThree = new BusinessUnit("AEG");

		businessUnitThree.addEmployee(new Employee(765850L, "Sansa", "Stark", "F",
				"sansa.stark@users.noreply.github.com", parseDate("8/18/1991"), parseDate("5/15/2017"), 113256L));
		businessUnitThree.addEmployee(new Employee(262382L, "Yara", "Greyjoy", "F",
				"yara.greyjoy@users.noreply.github.com", parseDate("4/20/1985"), parseDate("7/30/2007"), 64143L));
		businessUnitThree.addEmployee(new Employee(870820L, "Olena", "Tyrell", "F",
				"olena.tyrell@users.noreply.github.com", parseDate("3/28/1991"), parseDate("3/3/2017"), 116629L));

		businessUnitRepository.save(businessUnitThree);

	}

	@Override
	public int getOrder() {
		return 1;
	}

	private static Date parseDate(String strDate) {

		Date date = null;

		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}

		return date;
	}

}
