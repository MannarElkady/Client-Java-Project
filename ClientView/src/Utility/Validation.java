/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DELL
 */
public class Validation {

    public static boolean checkEmailRegex(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        System.out.println(email + " : " + matcher.matches());
        return matcher.matches();
    }

    public static boolean checkString(String str) {
        if(!(str.trim().isEmpty()) && !(str.trim().equals(""))) {
            return true;
        }
        return false;
    }

    public static boolean checkDeadlineItem(Date taskDate, Date todoASsign, Date todoDeadline) {
        if(taskDate.after(todoASsign) && taskDate.before(todoDeadline)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDeadlineAssignTodo(Date todoASsign, Date todoDeadline) {
        if(todoDeadline.after(todoASsign)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkUsernameRegex(String username) {
        String regex = "[a-zA-Z0-9\\._\\-]{5,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        System.out.println(username + " : " + matcher.matches());
        return matcher.matches();
    }
}
