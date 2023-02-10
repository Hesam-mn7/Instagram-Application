package com.alroid.instagramhesam.Exception;

public class Exceptions {

    public static class UsernameWeakLenghtException extends RuntimeException {
        public UsernameWeakLenghtException() {
            super("Your username must be 3 characters or more.");
        }
    }

    public static class PasswordWeakLenghtException extends RuntimeException {
        public PasswordWeakLenghtException() {
            super("For security, your password must be 6 characters or more.");
        }
    }
    public static class EmailWeakLenghtException extends RuntimeException {
        public EmailWeakLenghtException() {
            super("Please enter the email correctly." + "\n" + "for example: test@gmail.com");
        }
    }
    public static class PhoneNumberLenghtException extends RuntimeException {
        public PhoneNumberLenghtException() {
            super("Please enter the phone number correctly." + "\n" + "for example: 09121234567");
        }
    }
    public static class NameLenghtException extends RuntimeException {
        public NameLenghtException() {
            super("Please enter the name.");
        }
    }
    public static class BioLenghtException extends RuntimeException {
        public BioLenghtException() {
            super("Please enter the bio.");
        }
    }
    public static class NoProfilePicException extends RuntimeException {
        public NoProfilePicException() {
            super("Please select the profile pic.");
        }
    }
    public static class NoPicException extends RuntimeException {
        public NoPicException() {
            super("Please select the pic.");
        }
    }
    public static class CaptionLenghtException extends RuntimeException {
        public CaptionLenghtException() {
            super("Please enter the Caption");
        }
    }
}
