const path = require('path');
const electronPath = path.join(__dirname, '..',
    'node_modules', '.bin', 'electron');
const Application = require('spectron').Application;
const {expect} = require('chai');

const app = new Application({
  path: electronPath,
  args: [path.join(__dirname, '..')],
  env: {
    SPECTRON: true,
  },
  chromeDriverArgs: [
    '--no-sandbox',
    '--disable-dev-shm-usage',
    '--remote-debugging-port=9222',
    '--headless',
  ],
});

describe('sQReen Reader', function() {
  this.timeout(30000);

  before(()=>{
    return app.start();
  });

  after(()=>{
    return app.stop();
  });

  it('should have a visible window', ()=>{
    return app.client.getWindowCount().then((count)=>{
      expect(count).to.equal(1);
    });
  });

  it('should capture QR Codes and display links', async ()=>{
    await sleep(5000);
    const qrCodeElement = await app.client.$('#qr-code');
    const qrCodeLink = await qrCodeElement.getText();
    expect(qrCodeLink).to.equal('https://www.geeksforgeeks.org');

    const defaultMessageElement = await app.client.$('#no-qr-code');
    const style = await defaultMessageElement.getAttribute('style');
    expect(style).to.equal('visibility: hidden;');
  });

  it('should display QR code Image', async ()=>{
    await sleep(5000);
    const element = await app.client.$('#qr-code-image');
    const src = await element.getAttribute('src');
    expect(src).to.not.be.null;
  });
});

function sleep(ms) {
  return new Promise(((resolve) => {
    setTimeout(resolve, ms);
  }));
}
