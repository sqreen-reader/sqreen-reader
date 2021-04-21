const QrCodeReader = require('./qr-code-reader');
const QrCode = require('qrcode-reader');
const sinon = require('sinon');
const {expect} = require('chai')

describe('QrCodeReader', ()=>{
    describe('read', ()=>{
        it('should read a qr code and do callback', ()=>{
            const qrCode = new QrCode();
            const mock = sinon.mock(qrCode);
            mock.expects("decode").once().withExactArgs("data:/");

            const qrReader = new QrCodeReader(qrCode);

            const callback = () => {};
            qrReader.read(new MockNativeImage(), callback);
            mock.verify();
            expect(qrCode.callback).to.equal(callback);
        });
    });
});

/**
 * Mock Electron NativeImage class
 */
class MockNativeImage {
    constructor() {
    }

    toDataURL() {
        return "data:/";
    }
}