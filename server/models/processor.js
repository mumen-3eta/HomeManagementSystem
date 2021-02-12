const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');

const {Schema} = mongoose;

const ProcessorProductionSchema = new Schema({
    createdAt: {
        type: Date,
        default: Date.now,
    },
});

const ProcessorSchema = new Schema({
    ProcessorId: {
        type: Schema.Types.ObjectId,
        ref: 'ProcessorProduction',
    },
    userId: {
        type: Schema.Types.ObjectId,
        ref: 'User',
    },
});

ProcessorSchema.plugin(uniqueValidator);
ProcessorProductionSchema.plugin(uniqueValidator);

const Processor = mongoose.model('Processor', ProcessorSchema);
const ProcessorProduction = mongoose.model(
    'ProcessorProduction',
    ProcessorProductionSchema
);

module.exports = {
    Processor,
    ProcessorProduction,
};
