package com.example.SpringEmailDemo;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailDemoApplication {
	@Autowired
	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		String htmlContent = """
            <!DOCTYPE html>
            <html lang="vi">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Thông Tin Khách Hàng</title>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; }
                    h1 { color: #007bff; text-align: center; }
                    table { width: 100%; border-collapse: collapse; }
                    table, th, td { border: 1px solid #ddd; }
                    th, td { padding: 8px; text-align: left; }
                    th { background-color: #f1f1f1; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Thông Tin Khách Hàng</h1>
                    <p>Kính chào quý khách,</p>
                    <p>Dưới đây là thông tin chi tiết của bạn và danh sách sản phẩm:</p>
                    <table>
                        <tr><th>Họ và tên</th><td>Nguyễn Văn A</td></tr>
                        <tr><th>Email</th><td>nguyenvana@example.com</td></tr>
                        <tr><th>Số điện thoại</th><td>0123 456 789</td></tr>
                        <tr><th>Địa chỉ</th><td>123 Đường ABC, Quận XYZ, TP.HCM</td></tr>
                    </table>
                    <h2>Danh sách sản phẩm</h2>
                    <table>
                        <tr>
                            <th>Sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                        </tr>
                        <tr>
                            <td>Sản phẩm 1</td>
                            <td>2</td>
                            <td>500,000 VND</td>
                        </tr>
                        <tr>
                            <td>Sản phẩm 2</td>
                            <td>1</td>
                            <td>1,200,000 VND</td>
                        </tr>
                    </table>
                    <p>Xin chân thành cảm ơn!</p>
                    <p>Trân trọng,<br>Đội ngũ Hỗ trợ</p>
                </div>
            </body>
            </html>
            """;
		senderService.sendSimpleEmail("thinguyen99512@gmail.com",
				"This is email body",
				htmlContent);
	}
}
