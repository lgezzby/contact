package com.ssga.ssgai.contact;

import com.ssga.ssgai.contact.entity.Contact;
import com.ssga.ssgai.contact.utils.FileUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("(1)Initializing read contact file.");
        List<Contact> contactList = FileUtils.readFile();
        System.out.println("(3)Initializing contacts construction.");
        List<Contact> heads = FileUtils.constructContact(contactList);
        //Read input.
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        while (!command.equals("exit")) {
            if (command.equals("all")) {
                //iterate all employees
                FileUtils.printAllEmployees();
            } else {
                //print this employee
                FileUtils.printEmployee(command);
            }
            command = sc.next();
        }
        System.out.println("Program has already exit.");
    }
}
