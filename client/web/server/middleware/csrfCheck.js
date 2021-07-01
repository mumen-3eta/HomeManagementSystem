// const { boomify } = require('../utils');

// const csrfCheck = async (req, res, next) => {
//   try {
//     const { csrfToken } = req.session;
//     const receivedCsrfToken = req.headers['csrf-token'];

//     if (!receivedCsrfToken || csrfToken !== receivedCsrfToken) {
//       throw new Error('Provided CSRF-token is invalid');
//     }
//     next();
//   } catch (err) {
//     res
//       .status(401)
//       .json(boomify(401, 'CSRF has been attempted .', err.message));
//   }
// };

// module.exports = { csrfCheck };
