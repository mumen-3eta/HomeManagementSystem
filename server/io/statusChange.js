const {
  getControllerByControllerId,
  changeControllerStatus,
} = require('../database/queries');

module.exports.statusChange = (io,socket,controllerId) => async (obj) => {
  try {
    const {
      rows: [{status}],
    } = await getControllerByControllerId({ controllerId });

    const data ={ controllerId, newStatus:!status }

    const { rows: [controllerStatus] } = await changeControllerStatus(data);

    io.to(controllerId).emit('statusChangeReport', controllerStatus)

  } catch (error) {
    console.log(error);
  }
}