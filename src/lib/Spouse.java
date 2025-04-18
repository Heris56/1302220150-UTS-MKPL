/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib;

/**
 *
 * @author Haikal
 */
public class Spouse {

    private String name;
    private String idNumber;

    public Spouse(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public boolean isEmpty() {
        return (name == null || name.isBlank()) && (idNumber == null || idNumber.isBlank());
    }
}
