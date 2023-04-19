import { AppBar, Box, Button, CircularProgress, Container, CssBaseline, Unstable_Grid2 as Grid, Paper, ThemeProvider, Toolbar, Tooltip, Typography, createTheme } from '@mui/material'
import { amber, deepOrange } from '@mui/material/colors'
import { GetServerSidePropsContext } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import client from '../ts/ApiClient'
import { useState } from 'react'

interface HomeProps {
  cards: any[]
}

const theme = createTheme({
  palette: {
    background: {
      default: deepOrange[100]
    },
    primary: {
      main: deepOrange[900]
    }
  }

});

export default function Home({ cards }: HomeProps) {
  const [character, setCharacter] = useState();
  const [loading, setLoading] = useState(false);

  const handleBuildCharacter = async () => {
    setLoading(true);
    let params = new URLSearchParams();
    params.append("card", cards[0].shortName);
    params.append("card", cards[1].shortName);
    params.append("card", cards[2].shortName);
    const res = await client.get('/character', { params: params })
    setCharacter(res.data);
    setLoading(false);
  }

  return (
    <>
      <Head>
        <title>Charactarot</title>
      </Head>
      <main >
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <AppBar position='static'>
            <Toolbar></Toolbar>
          </AppBar>
          <Container >
            <Grid container spacing={3} sx={{ mt: 1 }}>
              {cards.map(c => {
                return (
                  <Grid xs={12} sm={4} key={c.shortName} >
                    <Tooltip title={<Typography>{c.desc}</Typography>}>
                      <Box>
                        <Paper sx={{ width: 199, height: 340, mx: 'auto' }}>
                          <Image src={c.imageLink} alt="favicon.ico" width={199} height={340} style={{ borderRadius: 15 }} />
                        </Paper>
                        <Typography variant="h5" sx={{ textAlign: 'center' }}>{c.name}</Typography>
                      </Box>
                    </Tooltip>
                  </Grid>
                )
              })}
              {character &&
                <Grid sm={8} smOffset={2}>
                  <Paper sx={{ p: 1, mt: 1, background: amber[100] }}>
                    <Typography >{character}</Typography>
                  </Paper>
                </Grid>
              }
            </Grid>
            {loading &&
              <Box sx={{ display: 'flex', my: 1, width: "100%" }}>
                <CircularProgress size={35} sx={{ m: 'auto' }} />
              </Box>
            }
            <Box sx={{ display: 'flex', my: 3, width: "100%" }}>
              <Button onClick={handleBuildCharacter} variant='contained' sx={{ m: 'auto' }}>Build Character</Button>
            </Box>
          </Container>
        </ThemeProvider>
      </main>
    </>
  )
}

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const res = await client.get('/cards/random');
  return { props: { cards: res.data.cards } }
}
