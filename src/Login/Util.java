package Login;

public class Util {
    // Đường dẫn đến trình duyệt (Firefox, Chrome, Edge...)
    public static final String FIREFOX_DRIVER_PATH = "C:\\path\\to\\geckodriver.exe";
    public static final String CHROME_DRIVER_PATH = "C:\\Drivers\\chromedriver-win64\\chromedriver.exe";

    // URL của trang web cần test
    public static final String BASE_URL = "https://stage.moosocial.com/php81/nhan/shaun1n31/";

    // Thông tin đăng nhập (có thể load từ file `.properties`)
    public static final String USERNAME = "admin@socialloft.com";
    public static final String PASSWORD = "123456";
    public static final String WRONGPASSWORD = "12345546";

    // Thời gian chờ tối đa (giây)
    public static final int WAIT_TIME = 10;
}

