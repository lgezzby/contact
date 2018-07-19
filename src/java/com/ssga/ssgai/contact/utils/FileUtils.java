package com.ssga.ssgai.contact.utils;

import com.ssga.ssgai.contact.entity.Contact;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static String filePath = "contacts.txt";
    private static List<Contact> contactList = new ArrayList<Contact>();
    private static List<Contact> heads = new ArrayList<Contact>();
    //queue for iterate node like BFS
    private static ArrayQueue<Contact> queue = null;
    private static boolean flag = false;

    public static File getResourceFileByStatic(String path){
        String filePath = FileUtils.class.getClassLoader().getResource(path).getFile();
        File resouce = new File(filePath);
        return resouce;
    }

    public static List<Contact> readFile() {
        File file = getResourceFileByStatic(filePath);
        BufferedReader is = null;
        try {
            is = new BufferedReader(new FileReader(file));
            String tmp = null;
            String[] arr = null;
            Contact node = null;
            while ((tmp = is.readLine()) != null) {
                //System.out.println(tmp);
                arr = tmp.split(" ");
                //check arr length
                if (arr.length == 2) {
                    node = new Contact(arr[0], arr[1], null);
                } else {
                    node = new Contact(arr[0], arr[1], arr[2]);
                }
                contactList.add(node);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("(2)File read complete.");
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contactList;
    }

    public static List<Contact> constructContact(List<Contact> contactList) {
        //find the top node
        for (Contact contact : contactList) {
            if (contact.getPriorId() == null) {
                heads.add(contact);
            }
        }

        //iterator
        for (Contact head : heads) {
            queue = new ArrayQueue<>(contactList.size());

            int index = 0;
            queue.add(head);

            Contact priorNode = null;
            while (queue.size() != 0) {
                priorNode = queue.get(index);

                for (Contact node : contactList) {
                    if (node.getPriorId() != null && node.getPriorId().equals(priorNode.getId())) {
                        queue.add(node);
                        priorNode.getNextIds().add(node);
                    }
                }

                queue.remove(index);
            }
        }
        System.out.println("(4)Contacts construction complete.");
        return heads;
    }

    public static void printAllEmployees() {
        for (Contact head : heads) {
            flag = false;
            //iterate contact tree node
            int depth = 0;
            iterateAllTree(head, depth);
        }
        System.out.println("Print all employees complete.");
    }

    public static void printEmployee(String id) {
        for (Contact head : heads) {
            flag = false;
            //iterate contact tree node
            iterateTree(head, id);
        }
        System.out.println("Print employee complete.");
    }

    private static void iterateTree(Contact root, String id) {
        if (flag == true || root == null) {
            return;
        }

        //matches
        if (root.getId().equals(id)) {
            System.out.println(root.getName());
            for (Contact node : root.getNextIds()) {
                System.out.println("\t" + node.getName());
            }
            flag = true;
            return;
        }

        //not matches but has child or next
        if (root.getNextIds().size() != 0) {
            for (int index = 0; index < root.getNextIds().size(); index++) {
                iterateTree(root.getNextIds().get(index), id);
            }

        }
        //not match and not child and last one
        else {
            return;
        }
    }

    private static void iterateAllTree(Contact root, int depth) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(root.getName());

        //has child or next
        if (root.getNextIds().size() != 0) {
            depth++;
            for (int index = 0; index < root.getNextIds().size(); index++) {
                iterateAllTree(root.getNextIds().get(index), depth);
            }

        }
        //not match and not child and last one
        else {
            return;
        }
    }
}
