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

export default function Home({ cards }: HomeProps) {
  const [character, setCharacter] = useState();
  const [loading, setLoading] = useState(false);
  const [cardValues, setCardValues] = useState<any[]>(cards);

  const handleBuildCharacter = async () => {
    setLoading(true);
    let params = new URLSearchParams();
    params.append("card", cardValues[0].shortName);
    params.append("card", cardValues[1].shortName);
    params.append("card", cardValues[2].shortName);
    const res = await client.get('/character', { params: params })
    setCharacter(res.data);
    setLoading(false);
  }

  const handleCardClick = async (index: number) => {
    const res = await client.get('/cards/random', { params: { size: 1 } });
    const card = res.data.cards[0];
    const currentCards = [...cardValues];
    currentCards[index] = card;
    setCardValues(currentCards);
  }

  return (
    <>
      <Head>
        <title>Charactarot</title>
      </Head>
      <Grid container spacing={3} sx={{ mt: 1 }}>
        {cardValues && cardValues.map((c, index) => {
          return (
            <Grid xs={12} sm={4} key={c.shortName} >
              <Tooltip title={<Typography>{c.desc}</Typography>}>
                <Box>
                  <Paper onClick={() => handleCardClick(index)} sx={{ width: 199, height: 340, mx: 'auto', borderRadius: 3, overflow: 'hidden' }}>
                    <Image src={c.imageLink} alt="favicon.ico" width={199} height={340} />
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
        <Button onClick={handleBuildCharacter} disabled={loading} variant='contained' sx={{ m: 'auto' }}>Build Character</Button>
      </Box>
    </>
  )
}

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const res = await client.get('/cards/random');
  return { props: { cards: res.data.cards } }
}
