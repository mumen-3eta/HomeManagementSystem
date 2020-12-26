/* eslint-disable func-names */
const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');
const bcrypt = require('bcryptjs');

const { Schema } = mongoose;

const UserSchema = new Schema({
  basicInfo: {
    userName: {
      type: String,
      required: true,
      minlength: 6,
      trim: true,
    },
    email: {
      type: String,
      required: true,
      minlength: 1,
      trim: true,
      unique: true,
    },
    password: {
      type: String,
      required: true,
      minlength: 8,
    },
    createdAt: {
      type: Date,
      default: Date.now,
    },
    isAdmin: {
      type: Boolean,
      default: false,
    },
  },
  profileInfo: {
    firstName: {
      type: String,
      required: false,
      minlength: 1,
      trim: true,
    },
    lastName: {
      type: String,
      required: false,
      minlength: 1,
      trim: true,
    },
    image: {
      type: String,
      required: false,
      trim: true,
    },
    mobile: {
      type: Number,
      required: false,
      minlength: 7,
    },
  },
});

UserSchema.plugin(uniqueValidator);

UserSchema.pre('save', function (next) {
  const user = this;

  const bgColor = '4f4f4f';
  const fontColor = 'fff';
  user.profileInfo.image = `https://ui-avatars.com/api/?name=${user.basicInfo.userName}&color=${fontColor}&background=${bgColor}`;

  if (user.isModified('basicInfo.password')) {
    bcrypt
      .genSalt(12)
      .then((salt) => bcrypt.hash(user.basicInfo.password, salt))
      .then((hash) => {
        user.basicInfo.password = hash;
        next();
      })
      .catch((err) => next(err));
  }
});

module.exports = mongoose.model('User', UserSchema);
