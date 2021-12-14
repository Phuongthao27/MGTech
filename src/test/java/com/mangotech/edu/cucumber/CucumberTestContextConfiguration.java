package com.mangotech.edu.cucumber;

import com.mangotech.edu.MgtEduCenterApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = MgtEduCenterApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
