const connection = require('../../data/connection');

const updateUserData = ({
  firstName,
  lastName,
  image,
  email,
  userName,
  password,
  mobile,
  userId,
  isAdmin,
}) => {
  const sql = {
    text:
      'update users set first_name=COALESCE($1,first_name ) ,last_name =COALESCE($2,last_name) ,image =COALESCE($3,image) ,email=COALESCE($4,email) ,user_name=COALESCE($5,user_name) ,password=COALESCE($6,password) ,mobile=COALESCE($7,mobile),is_admin=COALESCE($8,is_admin) where id=$9 returning user_name, email, first_name, last_name, image, mobile,is_admin',
    values: [
      firstName,
      lastName,
      image,
      email,
      userName,
      password,
      mobile,
      isAdmin,
      userId,
    ],
  };
  return connection.query(sql);
};

module.exports = updateUserData;
