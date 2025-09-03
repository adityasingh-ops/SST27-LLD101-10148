public class Decoder {

    public Frame decode(byte[] fileBytes) {
        return new Frame(fileBytes);
    }
}
