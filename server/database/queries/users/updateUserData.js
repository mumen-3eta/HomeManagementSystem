const connection = require('../../data/connection');

const updateUserData = (data) => {
  const {
    firstName,
    lastName,
    image,
    email,
    userName,
    password,
    mobile,
    userId,
  } = data;
  const sql = {
    text:
      'update users set first_name=COALESCE($1,first_name ) ,last_name =COALESCE($2,last_name) ,image =COALESCE($3,image) ,email=COALESCE($4,email) ,user_name=COALESCE($5,user_name) ,password=COALESCE($6,password) ,mobile=COALESCE($7,mobile) where id=$8 returning user_name, email, first_name, last_name, image, mobile',
    values: [
      firstName,
      lastName,
      image,
      email,
      userName,
      password,
      mobile,
      userId,
    ],
  };
  return connection.query(sql);
};

module.exports = updateUserData;
