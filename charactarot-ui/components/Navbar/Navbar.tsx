import { AppBar, Avatar, Box, Button, FormControlLabel, FormGroup, IconButton, Menu, MenuItem, Paper, Switch, Toolbar, Typography } from "@mui/material"
import CastleIcon from '@mui/icons-material/Castle';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import LightModeIcon from '@mui/icons-material/LightMode';
import { useState, MouseEvent, FormEvent, ChangeEvent } from "react";
import { signIn, signOut, useSession } from "next-auth/react";
import { Divider, Unstable_Grid2 as Grid } from "@mui/material";
import { useRouter } from "next/router";

interface Props {
    darkMode: boolean;
    handleThemeChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

export const Navbar = ({ darkMode, handleThemeChange }: Props) => {
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const router = useRouter()
    const open = Boolean(anchorEl);
    const { data: session } = useSession();

    const handleClick = (event: MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleNavigate = (url: string) => {
        const router = useRouter();
        router.push(url);
    }

    return (
        <AppBar position='sticky'>
            <Toolbar>
            <Grid container sx={{ width: '100%', alignItems: 'left' }} >
            <Grid  sm={6}>
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <IconButton
                        size='large'
                        edge='start'
                        onClick={() => router.push('/')}
                        sx={{ mr: 2 }}>
                        <CastleIcon fontSize="large" />
                    </IconButton>
                    <Typography variant='h6' >
                        Charactarot
                    </Typography>
                </Box>
            </Grid>
            <Grid sm={2}>
                <Box sx={{display: 'flex', alignItems: 'left' }}>
                    <Button
                        onClick={() => router.push('/tarot')}
                        sx={{mr: 2 }}>
                            <Typography variant='h6'
                                sx={{ color: 'white '}}>
                                Tarot
                            </Typography>
                    </Button>
                </Box>
            </Grid>
            <Grid sm={6}>
                <Box sx={{
                    display: 'flex',
                    flexGrow: 1,
                    justifyContent: 'flex-end'
                }}>
                    <FormGroup>
                        <FormControlLabel
                            sx={{ display: 'flex', alignContent: 'center', height: '100%' }}
                            control={<Switch
                                defaultChecked
                                checked={darkMode}
                                onChange={handleThemeChange}
                            />}
                            label={darkMode
                                ? <DarkModeIcon sx={{ mt: .5 }} />
                                : <LightModeIcon sx={{ mt: .5 }} />}
                        />
                    </FormGroup>
                    {session &&
                        <IconButton onClick={handleClick}>
                            <Avatar src={session.user?.image} />
                        </IconButton>
                        ||
                        <Button
                            onClick={() => signIn('auth0')}
                            variant='contained'
                            color='secondary'
                            sx={{ height: '100%' }}>
                            Login
                        </Button>
                    }
                    <Menu
                        open={open}
                        anchorEl={anchorEl}
                        onClose={handleClose}
                        id='profile-menu'
                    >
                        <MenuItem>

                        </MenuItem>
                        <Divider />
                        <MenuItem onClick={() => signOut()}>
                            Logout
                        </MenuItem>
                    </Menu>
                </Box>
            </Grid>
        </Grid>

            </Toolbar>
        </AppBar>
    )
}