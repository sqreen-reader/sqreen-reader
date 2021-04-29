class ReadQrCodeHandler {
  constructor(screenCapturer, qrCodeReader) {
    this.screenCapturer = screenCapturer;
    this.qrCodeReader = qrCodeReader;
  }

  handle(callback) {
    this.screenCapturer.capture().then((source) => {
      this.qrCodeReader.read(source[0].thumbnail, callback);
    });
  }
}

module.exports = ReadQrCodeHandler;
