const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');

const { Schema } = mongoose;

const UserControllerSchema = new Schema({
  name: {
    type: String,
    trim: true,
  },
  status: {
    type: Boolean,
    default: false,
  },
  ProcessorId: {
    type: Schema.Types.ObjectId,
    ref: 'Processor',
  },
  typeId: {
    type: Schema.Types.ObjectId,
    ref: 'ControllerType',
  },
  locationId: {
    type: Schema.Types.ObjectId,
    ref: 'ControllerLocation',
  },
  controllerId: {
    type: Schema.Types.ObjectId,
    ref: 'ControllerProduction',
  },
});

const ControllerProductionSchema = new Schema({
  createdAt: {
    type: Date,
    default: Date.now,
  },
});

const ControllerTypeSchema = new Schema({
  label: {
    type: String,
    required: true,
    unique: true,
    minlength: 2,
    trim: true,
  },
});

const ControllerLocationSchema = new Schema({
  label: {
    type: String,
    required: true,
    unique: true,
    minlength: 2,
    trim: true,
  },
});

const myPlugins = [
  UserControllerSchema,
  ControllerTypeSchema,
  ControllerProductionSchema,
  ControllerLocationSchema,
];

myPlugins.forEach((schema) => schema.plugin(uniqueValidator));

const UserController = mongoose.model('UserController', UserControllerSchema);
const ControllerType = mongoose.model('ControllerType', ControllerTypeSchema);
const ControllerLocation = mongoose.model(
  'ControllerLocation',
  ControllerLocationSchema
);
const ControllerProduction = mongoose.model(
  'ControllerProduction',
  ControllerProductionSchema
);

module.exports = {
  UserController,
  ControllerType,
  ControllerLocation,
  ControllerProduction,
};
