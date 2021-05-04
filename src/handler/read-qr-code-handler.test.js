const ReadQrCodeHandler = require('./read-qr-code-handler');
const {ScreenCapturer} = require('../capture/screen-capture');
const QrCodeReader = require('../qrcode/qr-code-reader');
const sinon = require('sinon');

describe('ReadQrCodeHandler', ()=>{
  describe('handle', ()=>{
    it('reads qr codes and executes callbacks', (done)=>{
      const screenCapturer = new ScreenCapturer();
      const qrCodeReader = new QrCodeReader();

      sinon.stub(screenCapturer, 'capture').resolves([{
        thumbnail: 0,
      }]);

      const callback = () => done();
      qrCodeReader.read = callback;

      const handler = new ReadQrCodeHandler(screenCapturer, qrCodeReader);
      handler.handle(callback);
    });
  });
});
