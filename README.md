# SID-18-repo-411f9bea-1245-45e4-9e97-7d8483d5aa93

### Assignment 1:

# Employee Payroll Calculator

## Description
A simple *Java-based payroll calculator* that computes monthly salary for employees based on their type (FULL_TIME, PART_TIME, CONTRACTOR).  
It applies progressive tax brackets, optional deductions, and generates a detailed payslip including gross pay, tax, deductions, and net pay.  

---

## How to Run

### Run Application
Compile and run the PayrollTest class for testing the payslips for combination of each type of employee with tax brackets and optional deductions.

---

### Assignment 2:
### Document Analyser

This project implements a Text Document Analyzer in Java that reads a plain text file, normalizes its content, analyzes word usage, and generates comprehensive word statistics.
The solution is designed using object-oriented principles, clean separation of responsibilities, and includes unit tests.

#### How to Run the Application

#### 1. Compile

From the project root:

`rm -rf out`

`javac -d out src/main/java/analyser/*.java`

#### 2. Run

`java -cp out analyser.Main resources/sample_input.txt resources/output.txt`

#### Output

A formatted report will be written to:

resources/output.txt