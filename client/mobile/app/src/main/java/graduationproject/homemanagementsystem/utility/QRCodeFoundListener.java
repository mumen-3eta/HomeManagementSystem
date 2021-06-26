package graduationproject.homemanagementsystem.utility;

public interface QRCodeFoundListener {
    void onQRCodeFound(String qrCode);
    void qrCodeNotFound();
}
