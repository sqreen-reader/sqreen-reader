class QrCodeReader {
    constructor(qrCode) {
        this.qrCode = qrCode;
    }

    read(image, callback) {
        this.qrCode.callback = callback;
        this.qrCode.decode(image.toDataURL());
    }
}

module.exports = QrCodeReader;