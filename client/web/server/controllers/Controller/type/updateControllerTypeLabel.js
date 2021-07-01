const boom = require('@hapi/boom');

const {
  updateControllerType,
  getControllerTypeByLabel,
  getControllerType,
} = require('../../../database/queries');

const updateControllerTypeLabel = async (req, res, next) => {
  try {
    const { label, typeId } = req.body;
    const {
      rows: [typeData],
    } = await getControllerTypeByLabel({ typeLabel: label });

    if (typeData) {
      return next(boom.conflict('this type is already there'));
    }
    const {
      rows: [typeIdData],
    } = await getControllerType({ typeId });

    if (!typeIdData) {
      return next(boom.conflict('this type id is not valid'));
    }

    const { rows: data } = await updateControllerType({ label, typeId });

    return res.status(201).json({
      title: 'updating Controller Type',
      detail: 'Controller Type renamed Successfully',
      data,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = updateControllerTypeLabel;
