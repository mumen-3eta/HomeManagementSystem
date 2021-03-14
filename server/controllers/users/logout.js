const Login = async (req, res, next) => {
    return res
      .clearCookie('token')
      .status(201)
      .send({
        title: 'User logout',
        detail: 'Successfully loged-out ',
      }); 
};

module.exports = Login;
