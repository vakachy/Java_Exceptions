package HW_3;

public class ProgramExceptions extends RuntimeException{
    public ProgramExceptions(String message) {
        super(message);
    }
}

class noDataException extends ProgramExceptions{
    public noDataException(String message) {super(message);}
}
class yearOutOfRange extends ProgramExceptions {
    public yearOutOfRange(String message) {
        super(message);
    }
}

class monthOutOfRange extends ProgramExceptions {
    public monthOutOfRange(String message) {
        super(message);
    }
}

class dayOutOfRange extends ProgramExceptions {

    dayOutOfRange(String message){
        super(message);
    }
}

class telephoneNumberException extends ProgramExceptions{
    telephoneNumberException(String message) {super(message);}
}

class genderIncorrectInput extends ProgramExceptions{
    genderIncorrectInput(String message) {super(message);}
}