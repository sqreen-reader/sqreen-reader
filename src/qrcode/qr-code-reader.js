const jsQR = require('jsqr');
const jpeg = require('jpeg-js');
const QRCode = require('qrcode');

class QrCodeReader {
  read(image, callback) {
    const rawImageData = jpeg.decode(image.toJPEG(100));
    const qrCode = jsQR(rawImageData.data, rawImageData.width,
        rawImageData.height);
    if (qrCode && !this.isEmpty(qrCode.data)) {
      QRCode.toDataURL(qrCode.data, (err, url)=>{
        callback({
          data: qrCode.data,
          image: url,
        });
      });
    }
  }

  isEmpty(str) {
    return !str || str === '';
  }
}

module.exports = QrCodeReader;
