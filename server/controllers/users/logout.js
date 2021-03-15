const logout = (req, res) => {
  res
    .clearCookie('token')
    .json({ statusCode: 200, title: 'User logout', message: 'logout successfully' });
};

module.exports = logout;
