package com.example.Studentdatamanagement.service;

import com.example.Studentdatamanagement.Repo.StudentRepository;
import com.example.Studentdatamanagement.entity.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private StudentRepository repository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "F:\\Nimbus\\Assignment 1\\Reports"; // Remove the double quotes

        List<Student> students = repository.findAll();

        // Load files and compile.
        File file = ResourceUtils.getFile("classpath:students.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(students);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Createdby", "RajaniNavoda");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\students.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\students.pdf"); // Use exportReportToPdfFile
        }

        return "Report generated in path: " + path;
    }
}
