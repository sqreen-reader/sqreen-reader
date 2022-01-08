describe('SQReen Reader', () => {
  it('should open app', async ()=> {
    const message = $('#no-qr-code');
    await expect(message).toBeExisting();
    await expect(message).toHaveTextContaining('Ready to scan QR codes!');
  });

  it('should read qr codes', async () => {
    await sleep(5000);
    const qrCodeElement = $('#qr-code');
    await expect(qrCodeElement).toHaveText('https://www.geeksforgeeks.org');

    const defaultMessageElement = $('#no-qr-code');
    await expect(defaultMessageElement)
        .toHaveAttr('style', 'visibility: hidden;');
  });

  it('should display QR code Image', async ()=>{
    await sleep(5000);
    const element = $('#qr-code-image');
    expect(element).toHaveAttr('src');
  });
});

function sleep(ms) {
  return new Promise(((resolve) => {
    setTimeout(resolve, ms);
  }));
}
