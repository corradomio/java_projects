package com.example;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class App2 {
    static double[][] D = new double[][]{{145.67544171446437, 166.23343109163778, 107.4241316168394, 289.05850586434826, 371.8541379487697, 270.9950555870972, 267.8799872748043, 417.8546138891023, 350.5802279752383, 107.374959471835, 147.82274525640307, 44.89948990692033, 173.27486170421207, 536.9887720007781, 296.9111583512791, 223.36028712051564, 526.3530648290783, 591.6820156607004, 77.08445524420863, 693.6973278291063, 768.0247141364329, 584.6383376301828},
        {152.2524105631039, 207.8531611151186, 203.19483989283862, 372.67954686657794, 460.8284049190874, 335.71000634420307, 363.34434012412146, 312.8109883284257, 270.5058517979514, 0.0, 171.06062407005618, 65.32784106928344, 67.5037686184035, 610.6676524828433, 403.9629678584294, 329.0753280960743, 506.2885505718306, 490.28006752037226, 127.00439939892077, 628.8104033404309, 696.4474253039803, 546.1775486583933},
        {302.28998774034113, 364.8235674603381, 441.28662933273233, 551.6580192607627, 641.0532711121878, 620.8156131923666, 648.4452334111156, 108.10746575686997, 74.1103291636621, 288.04945367154426, 449.00290844792016, 337.52931944565637, 224.8629236746906, 731.5131848359039, 668.723096033987, 601.6608171789777, 394.9632094693036, 219.09291536071933, 350.1152834443423, 379.2218625365791, 430.1217594732979, 374.96855415324853},
        {181.4550571792236, 135.37170614297, 49.34576160923962, 139.35860741600055, 216.70123907508986, 325.2761732379548, 249.2621091537495, 540.6190930632596, 430.76087890265114, 252.286156759521, 276.8815521598235, 188.13881586219006, 309.3681082541914, 390.2385019411714, 201.54078881832004, 161.19840144538742, 502.30550910619235, 699.7565992009723, 136.0378759196177, 728.889076611457, 812.0730578033034, 583.8030450530468},
        {252.84133269339782, 192.45172209546615, 139.3038655550193, 74.23919057891244, 130.3898408021295, 377.1991978038195, 273.59554296377917, 621.7359868086426, 498.77437071654833, 341.52838416727553, 358.51767487351145, 277.87561115230466, 396.61887612709893, 313.9325598777075, 180.131248253167, 178.35483689582802, 517.664275167741, 774.0682598615535, 221.06715630886342, 769.952370533502, 856.2994908532733, 609.3735692270327},
        {404.4661086676826, 381.16544696229425, 262.39696877131394, 344.50923098250524, 369.54225583049384, 159.4082728056327, 25.67484751783958, 695.867003657108, 635.8649228510903, 384.3260656351008, 271.25220013559857, 330.45416335155346, 451.6243327052486, 558.8158812616447, 117.98912203351811, 95.13460904953958, 756.5182261131877, 874.6018440509565, 334.86169149016195, 966.906082428445, 1045.6620095798592, 834.4210492705862},
        {479.9234063976556, 483.10536240999244, 371.2993273241178, 506.8579503647625, 548.52728682553, 65.08580158121102, 174.21523101869124, 675.5677178298047, 666.2265207124304, 395.7229243521222, 229.0064662466671, 367.39987387180724, 453.7006877482465, 739.2735352348215, 308.3063717137306, 260.61847089972724, 858.570519437897, 859.9231981189935, 407.92416072159347, 1022.4848486872313, 1091.9811332607264, 920.1197906554306},
        {731.1771070245982, 799.1366926788601, 845.2651439692484, 988.325841934701, 1079.435994349837, 905.1824901469074, 986.2486675612588, 361.14484905572715, 527.5268564145746, 647.7160869694874, 742.553120760692, 712.5548954918759, 584.4674131330988, 1184.3346357810046, 1049.6877888467375, 972.2200240322564, 811.5492868235987, 286.8165000120427, 756.5541938700147, 638.0979008149434, 619.4544727903104, 751.6569754501671},
        {579.838284153286, 642.7339686749622, 715.2669074790697, 828.9586084292745, 917.7499334023643, 852.7550992223092, 903.6451051432057, 229.96823088730363, 341.2212828878733, 541.370759977721, 678.8714499842845, 599.7466158804773, 473.89126400295737, 996.5075451427213, 938.8318833206173, 867.8633156684319, 584.1967770275376, 61.29699072754555, 623.545170856227, 400.8114544072406, 390.6612845887504, 516.6413743812475},
        {641.6872836127553, 676.7216163790055, 788.3527560252843, 819.1563532122732, 889.6295108187368, 1030.8379483049564, 1030.7566003253646, 487.1046501003993, 426.36836277044995, 696.4474253039803, 866.3077329861396, 729.2158178913805, 642.6387183901289, 890.4144086182567, 1013.6137405228707, 963.227608475318, 393.26726395880974, 367.6053781471955, 710.8890314860514, 90.51492532239833, 0.0, 285.90591058287276},
        {491.9841684888803, 517.4084580005288, 633.5913368539099, 646.7846060584959, 714.0278852918269, 898.2509067327547, 882.3756042927018, 438.5323126173652, 317.8806329892182, 572.9278256548846, 743.6337239630645, 594.2937991111231, 529.3954732887482, 711.7253027270427, 852.6475466844348, 808.2544887883905, 215.54896100636714, 388.45337170664413, 563.9740597871813, 88.45896316308625, 178.89862621409523, 110.39908669069942},
        {292.7559983981154, 333.91042267717984, 440.6100792984739, 497.33108221364733, 578.7899370431126, 685.0991852905556, 680.8191709859361, 267.4985444304545, 105.75585279538055, 355.54242670222266, 526.5609767414425, 381.5294616297749, 311.06864303992796, 631.0641385438441, 668.7097943110488, 614.8028843316647, 235.40942013283367, 311.60561163840435, 361.05261805821584, 275.15417190070013, 349.9500885569808, 217.5046082277473},
        {152.2524105631039, 207.8531611151186, 203.19483989283862, 372.67954686657794, 460.8284049190874, 335.71000634420307, 363.34434012412146, 312.8109883284257, 270.5058517979514, 0.0, 171.06062407005618, 65.32784106928344, 67.5037686184035, 610.6676524828433, 403.9629678584294, 329.0753280960743, 506.2885505718306, 490.28006752037226, 127.00439939892077, 628.8104033404309, 696.4474253039803, 546.1775486583933},
        {401.58695553969534, 386.8847053396368, 268.00063270593597, 374.4178912396467, 408.2102182708705, 108.16085180434104, 30.673756982301697, 670.0710184823031, 622.4862826903949, 362.31764007617517, 233.09386180577982, 314.0045403143169, 428.58349636551577, 598.6705955604623, 165.02464675968335, 122.23962073413095, 765.5266266089375, 851.1484214662548, 329.7053834216728, 962.4313737127079, 1038.8097659845953, 838.4941990796061},
        {374.94600160779345, 357.45083216009635, 238.30007804493377, 342.25556805922275, 377.57275419163005, 128.52559118064187, 18.535006070072928, 655.4794243211819, 600.6738269923138, 344.9922351693126, 228.37059504391638, 293.32856008955366, 412.00763126254134, 568.2172768514712, 140.5089597015014, 90.49218904209219, 735.566395801463, 835.1046961102165, 303.7744278944862, 936.8479329217344, 1014.2157474940102, 809.9047229555642},
        {341.6563336157064, 297.1120017898372, 195.1434767930842, 207.3307269905156, 223.25762368112723, 284.8669062732484, 157.3836931922979, 685.6959838232434, 589.8529338559001, 378.879588730607, 329.4222436448215, 314.62775548242024, 443.79416334882563, 412.5144936834122, 43.00464842401749, 77.27139848990925, 650.649521166729, 852.7514897740364, 285.8900900721264, 889.7553552610822, 973.6563934002036, 738.4274569680489},
        {215.71743862293522, 202.26854865527818, 86.62981497794614, 245.46338171403855, 313.06339465677235, 220.94553209709738, 175.09466890714666, 522.3699989572184, 447.3949748298802, 211.01954000178083, 176.59990125871695, 149.2748409483093, 277.61131877228297, 494.97168034395474, 193.05980682126363, 118.94970622045263, 581.2300075295219, 695.5306545992825, 145.0720953142925, 777.8814646257739, 855.9608983367064, 652.0560958576474},
        {188.56503413109814, 194.5000465200893, 102.01056608415375, 282.08381359661627, 357.80760723665827, 227.05139699131774, 215.7263791208157, 466.7039740628957, 402.64932830151565, 154.0859844813268, 134.73519370841598, 96.08529981784295, 221.36507613634316, 532.9136085699434, 251.49007597207168, 175.17540288576836, 566.5291707686979, 642.2787527683207, 116.0446556517245, 743.6867482632596, 818.9060041799924, 629.484116291844},
        {258.9290694229918, 245.67663681518684, 128.49064715757316, 271.6734748778508, 330.6726561096743, 182.5993376988461, 132.63575943230592, 553.2680424870605, 486.72646767908736, 240.46162963605886, 171.7690015801531, 182.8271751576172, 307.9122317014337, 517.270778450864, 171.56284565270764, 92.06211669550522, 624.7069420640752, 729.2527136914897, 187.5756025296263, 820.6502061760369, 898.142859536246, 695.8437965120248},
        {299.38814894618775, 302.3428554534941, 194.58765987199337, 352.25295344843215, 412.060377737102, 116.3080923556956, 137.3437448384626, 541.0683775172732, 501.514371978998, 235.80086440428386, 110.88626482653322, 194.03963844125124, 300.97333722498007, 598.7342923012549, 229.24205208462675, 151.55147969655758, 677.2029158645911, 722.6486212034304, 226.9887797464732, 850.1896139890223, 923.2530077307881, 740.2161734395577},
        {289.2876573496171, 273.20805161425545, 154.60258238151548, 282.6353770028658, 334.38735895736556, 166.70655975246834, 101.66139938762649, 582.6671418763827, 517.9708658701362, 269.93370593061223, 186.6702743292643, 213.47691628766054, 337.43704115572257, 523.407195108086, 150.52207272523057, 70.63172582907085, 652.0453812134308, 759.4288134489336, 218.35289212008215, 851.3537639432693, 929.1161032513353, 724.7995779222821},
        {215.71743862293522, 202.26854865527818, 86.62981497794614, 245.46338171403855, 313.06339465677235, 220.94553209709738, 175.09466890714666, 522.3699989572184, 447.3949748298802, 211.01954000178083, 176.59990125871695, 149.2748409483093, 277.61131877228297, 494.97168034395474, 193.05980682126363, 118.94970622045263, 581.2300075295219, 695.5306545992825, 145.0720953142925, 777.8814646257739, 855.9608983367064, 652.0560958576474}};

    static double[][] X = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 414, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 0, 0, 186, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 112, 221, 0, 0, 0, 570, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 13, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 260, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 175, 0, 298, 0, 0, 0, 0, 0, 0, 34, 24, 156, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 267, 128, 0, 0, 3, 20, 1, 58, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 822, 0, 0, 15, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 618},
        {127, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 405, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 221, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 3, 0, 211, 0, 0, 0, 0, 9, 0, 2, 0, 24, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 163, 0, 0, 0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 0, 0, 0},
        {5, 158, 140, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 63, 0, 0, 0},
        {121, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 56, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0},
        {6, 1, 272, 324, 0, 0, 0, 0, 0, 7, 0, 12, 0, 59, 0, 0, 0, 0, 119, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 0, 319, 86, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 63, 47, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 0, 0},
        {164, 238, 175, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 115, 0, 0, 0}};

    public static void main(String[] args) {
        RealMatrix Md = MatrixUtils.createRealMatrix(D);
        RealMatrix Mx = MatrixUtils.createRealMatrix(X);
    }
}