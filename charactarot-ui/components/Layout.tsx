import { Container, CssBaseline, ThemeProvider, createTheme } from "@mui/material";
import { ChangeEvent, PropsWithChildren, useEffect, useState } from "react";
import { Navbar } from "./Navbar/Navbar";
import { deepOrange } from "@mui/material/colors";

const themeLight = createTheme({
    palette: {
        background: {
            default: deepOrange[100]
        },
        primary: {
            main: deepOrange[900],
        }
    }

});

const themeDark = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: deepOrange[400]
        }
    }

});


export const Layout = ({ children }: PropsWithChildren) => {
    const [darkMode, setDarkMode] = useState(false);

    const handleThemeChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { checked } = e.target;
        localStorage.setItem('darkMode', checked.toString());
        setDarkMode(checked);
    }

    useEffect(() => {
        setDarkMode(localStorage.getItem('darkMode') === 'true')
    }, [])

    return (
        <main >
            <ThemeProvider theme={darkMode ? themeDark : themeLight}>
                <CssBaseline />
                <Navbar darkMode={darkMode} handleThemeChange={handleThemeChange} />
                <Container>
                    {children}
                </Container>
            </ThemeProvider>
        </main >
    )
}