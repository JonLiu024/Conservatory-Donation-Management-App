package exception;
//Representing an exception class that are thrown when the donor ID created by the user already existed in
//the donor list of conservation site

public class ExistingDonorIDException extends Exception {


    //REQUIRES: msg is not null
    //EFFECT: create an existingDonorIDException, the message is set as msg
    public ExistingDonorIDException(String msg) {
        super(msg);
    }

}
