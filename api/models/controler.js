const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');

const { Schema } = mongoose;

const UserControleSchema = new Schema({
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
    ref: 'ControlerType',
  },
  locationId: {
    type: Schema.Types.ObjectId,
    ref: 'ControlerLocation',
  },
  controlerId: {
    type: Schema.Types.ObjectId,
    ref: 'ControleProduction',
  },
});

const ControleProductionSchema = new Schema({
  createdAt: {
    type: Date,
    default: Date.now,
  },
});

const ControlerTypeSchema = new Schema({
  lable: {
    type: String,
    required: true,
    unique: true,
    minlength: 2,
    trim: true,
  },
});

const ControlerLocationSchema = new Schema({
  lable: {
    type: String,
    required: true,
    unique: true,
    minlength: 2,
    trim: true,
  },
});

const myPlugins = [
  UserControleSchema,
  ControlerTypeSchema,
  ControlerLocationSchema,
  ControleProductionSchema,
];

myPlugins.forEach((schema) => schema.plugin(uniqueValidator));

const UserCotroler = mongoose.model('UserCotroler', UserControleSchema);
const ControlerType = mongoose.model('ControlerType', ControlerTypeSchema);
const ControlerLocation = mongoose.model('ControlerLocation', ControlerLocationSchema);
const ControleProduction = mongoose.model('ControleProduction', ControleProductionSchema);

module.exports = {
  UserCotroler,
  ControlerType,
  ControlerLocation,
  ControleProduction,
};
