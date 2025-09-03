public class Demo10 {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        new ReportService(logger).generate();
    }
}
