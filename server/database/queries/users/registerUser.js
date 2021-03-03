const { connection } = require('../../data/index');

const signUp = (userData) => {
  const { basicInfo, profileInfo } = userData;
  const sql = {
    text:
      'INSERT INTO users ( email, user_name, password, first_name, last_name, mobile ) values($1 ,$2, $3, $4, $5, $6) returning id;',
    values: [
      basicInfo.email,
      basicInfo.userName,
      basicInfo.password,
      profileInfo.firstName,
      profileInfo.lastName,
      profileInfo.mobile,
    ],
  };
  return connection.query(sql);
};

module.exports = signUp;
