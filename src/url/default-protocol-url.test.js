const DefaultProtocolURL = require('./default-protocol-url');
const {expect} = require('chai');

describe('DefaultProtocolURL', ()=>{
  it('constructs urls with a protocol', ()=>{
    const urlWithProtocol = 'http://www.sqreen-reader.github.io/';
    const url = new DefaultProtocolURL(urlWithProtocol);
    expect(url.href).to.equal(urlWithProtocol);
  });

  it('constructs urls without a protocol using the default', ()=> {
    const urlWithoutProtocol = 'www.sqreen-reader.github.io/';
    const url = new DefaultProtocolURL(urlWithoutProtocol);
    expect(url.href).to.equal(`https://${urlWithoutProtocol}`);
  });

  it('does not construct invalid urls', ()=>{
    const invalidUrl = 'not a url';
    expect(()=>new DefaultProtocolURL(invalidUrl)).to.throw(/Invalid URL/i);
  });
});
