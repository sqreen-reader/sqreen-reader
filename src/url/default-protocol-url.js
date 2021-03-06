class DefaultProtocolURL extends URL {
  constructor(url) {
    try {
      super(url);
    } catch (_) {
      // eslint-disable-next-line constructor-super
      super(`https://${url}`); // NOSONAR
    }
  }
}

module.exports = DefaultProtocolURL;
