class ReadQrCodeHandler {
  constructor(screenCapturer, qrCodeReader) {
    this.screenCapturer = screenCapturer;
    this.qrCodeReader = qrCodeReader;
  }

  handle(callback) {
    this.screenCapturer.capture().then((sources) => {
      sources.forEach((source)=>{
        this.qrCodeReader.read(source.thumbnail, callback);
      });
    });
  }
}

module.exports = ReadQrCodeHandler;
