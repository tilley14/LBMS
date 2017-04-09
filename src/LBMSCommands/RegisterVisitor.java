package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Registers a new visitor so that they can access the library. Visitors are assigned a unique, 10-digit ID by the LBMS.
 *
 * Command format: clientID,register,first name,last name,address, phone-number.
 *
 * @author Kyler Freas
 * @author Nikolas Tilley
 */
public class RegisterVisitor implements LBMSCommand
{
    private Library library;
    // Todo private Long clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    /**
     * Creates a RegisterVisitor command the registers a new visitor with the LBMS system.
     *
     * @param library - The library the visitor is registering with.
     * @param firstName - the first name of the visitor.
     * @param lastName - the last name of the visitor.
     * @param address - the address of the visitor.
     * @param phoneNumber - is the phone number of the visitor.
     */
    public RegisterVisitor(Library library, String firstName, String lastName, String address, String phoneNumber)
    {
        this.library = library;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Executes the RegisterVisitor command on the the library.
     */
    public void execute()
    {
        library.registerVisitor(firstName, lastName, address, phoneNumber);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
