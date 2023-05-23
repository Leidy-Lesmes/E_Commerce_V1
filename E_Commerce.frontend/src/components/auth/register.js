import React, { useState } from 'react';
import { Alert, Button, Snackbar, Stack, TextField, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { LoadingButton } from '@mui/lab';

import { submitRegister } from '../../services/auth';
import registerStyles from './register.module.css';

function Register() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [registerData, setRegisterData] = useState({
    userName: '',
    email: '',
    password: '',
    roles: ['admin'],
  });
  const [open, setOpen] = useState(false);
  const [wrongData, setWrongData] = useState({
    status: true,
    infoText: '',
  });

  const handleForm = (e) => {
    const { id, value } = e.target;
    setRegisterData((prevData) => ({
      ...prevData,
      [id]: value,
    }));
  };

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpen(false);
  };

  const register = () => {
    submitRegister({
      registerData,
      setRegisterData,
      setWrongData,
      setOpen,
      navigate,
      setLoading,
    });
  };

  return (
    <div className={registerStyles.container}>
      <Stack spacing={2} className={registerStyles.card} justifyContent="center" alignItems="center">
        <Typography variant="h4" component="h2" fontWeight={600} style={{ color: '#333', marginBottom: '1rem' }}>
          Crea tu cuenta
        </Typography>
        <img src="https://cdn-icons-png.flaticon.com/512/1055/1055647.png" alt="logo" height={100} />
        <TextField
          id="userName"
          autoComplete="off"
          onChange={handleForm}
          value={registerData.userName}
          label="Usuario"
          variant="outlined"
        />
        <TextField
          id="email"
          autoComplete="off"
          onChange={handleForm}
          value={registerData.email}
          label="Email"
          variant="outlined"
        />
        <TextField
          id="password"
          type="password"
          onChange={handleForm}
          value={registerData.password}
          label="Contraseña"
          variant="outlined"
        />
        {loading ? (
          <LoadingButton loading variant="contained" className="btn">
            Crear Cuenta
          </LoadingButton>
        ) : (
          <Button variant="contained" className="btn" onClick={register}>
            Crear cuenta
          </Button>
        )}
        <Button variant="text" style={{ color: 'red' }} href="/">
          Cancelar
        </Button>
        <Snackbar
          open={open}
          autoHideDuration={1500}
          onClose={handleClose}
          anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
        >
          <Alert onClose={handleClose} severity={wrongData.status ? 'error' : 'success'} sx={{ width: '100%' }}>
            {wrongData.infoText}
          </Alert>
        </Snackbar>
      </Stack>
    </div>
  );
}

export default Register;
