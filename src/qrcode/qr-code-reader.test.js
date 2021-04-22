const QrCodeReader = require('./qr-code-reader');
const jpeg = require('jpeg-js');
const sinon = require('sinon');
const {expect} = require('chai')
const proxyquire = require('proxyquire');

describe('QrCodeReader', ()=>{
    before(()=>{
        sinon.stub(jpeg, "decode").returns({
            data: [],
            height: 0,
            width: 0
        });
    });
    describe('read', ()=>{
        it('should read a qr code and do callback', (done)=>{
            const jsQRStub = sinon.stub();
            jsQRStub.returns({data: "hello"})

            const QrCodeReader = proxyquire.load(
                "./qr-code-reader.js", {
                    "jsqr": jsQRStub
                }
            );

            const qrReader = new QrCodeReader();

            qrReader.read(new MockNativeImage(), (result)=>{
                expect(result.data).to.equal('hello');
                done();
            });
        });

        it('should not run callback if there is no QR code', ()=>{
            const qrReader = new QrCodeReader();
            const callback = sinon.spy();

            qrReader.read(new MockNativeImage(), callback);

            expect(callback.called).to.equal(false);
        });
    });
});

/**
 * Mock Electron NativeImage class
 */
class MockNativeImage {
    constructor() {
    }

    toJPEG() {
        return "data";
    }
}