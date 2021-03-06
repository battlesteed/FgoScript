package fgoScript.entity.guda;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commons.util.PropertiesUtil;
import fgoScript.constant.PointInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fgoScript.constant.GameConstant;
import fgoScript.entity.CardComparator;
import fgoScript.entity.CommonCard;
import fgoScript.entity.PointColor;
import fgoScript.service.EventFactors;
import commons.util.GameUtil;

public class EventGudazi extends AbstractApGudazi {
	private static final Logger LOGGER = LogManager.getLogger(EventGudazi.class);
	private static final PointInfo POINT_INFO = PointInfo.getSpringBean();
	@Override
	public void intoAndSelect(int apNum, int acountNum) throws Exception {
		insertIntoEventRoom();
	}
	@Override
	public void fightAndStop(boolean rebootFlag, int apNum) throws Exception {
		fightAndBackForEvent(rebootFlag);
	}
	@Override
	public Point getSuppotServant() {
		return POINT_INFO.getpServantMix();
	}

	@Override
	public void fightOverMethod() {
	}

	private void insertIntoEventRoom() throws Exception {
    	GameUtil.mouseMoveByPoint(POINT_INFO.getpScrollRestDown());
        GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
        GameUtil.mouseMoveByPoint(POINT_INFO.getpScrollRestTop());
        GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
        // 进入活动本
        Point p4 = POINT_INFO.getpWeekEntrance01();
        GameUtil.mouseMoveByPoint(p4);
        GameUtil.mousePressAndReleaseForConfirm(KeyEvent.BUTTON1_DOWN_MASK);
        
        GameUtil.delay(GameConstant.DELAY*5);
        
        GameUtil.mouseMoveByPoint(POINT_INFO.getpScrollRestTop());
        GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
        GameUtil.mouseMoveByPoint(POINT_INFO.getpScrollRestDown());
        GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
        
        
        
        // 拉滚动条至目标本
        Point p7 = POINT_INFO.getpEventScrollLocate01();
        GameUtil.mouseMoveByPoint(p7);
        GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
        // 点击ap本
        Point p8 = POINT_INFO.getpEventBattleLocate01();
        GameUtil.mouseMoveByPoint(p8);
        GameUtil.mousePressAndReleaseForConfirm(KeyEvent.BUTTON1_DOWN_MASK);
    }
	
	private void fightAndBackForEvent(boolean rebootFlag) throws Exception {
		// 1回合数字高亮点
		Point ps1 = new Point(903, 79);// 颜色：200;200;200 Color c = new Color(200, 200, 200);
		Point ps2 = new Point(903, 69);// 颜色：255;255;255 Color c = new Color(255, 255, 255);
		Point ps3 = new Point(904, 74);// 颜色：255;255;255 Color c = new Color(255, 255, 255);
		List<Point> pointList = new ArrayList<>();
		pointList.add(ps1);
		pointList.add(ps2);
		pointList.add(ps3);
		int eveValue;
		int battleRounds = 0;
		int MaxRounds = 20;
		int rounds = EventFactors.battleRounds;
		// 第一回合
		LOGGER.info("准备战斗，等待。。。。");
		waitToAttack(null);
		LOGGER.info("第一回合开始：");
		boolean[] hases = {false,false};
		eveValue = GameUtil.getColorEveValue(pointList);
		if (rebootFlag) {
			DEFAULT_SKILL_COLORS = EventFactors.getDefaultSkillColorsFromFile();
		}else {
			DEFAULT_SKILL_COLORS = EventFactors.getSkillColors();
			EventFactors.writeDefaultSkillColors(DEFAULT_SKILL_COLORS);
		}
		String startegy;
		while (eveValue > GameConstant.THRESHOLD && battleRounds < MaxRounds) {
			battleRounds++;
			/*
			  连续使用技能组
			 */
			giveServantSkills(EventFactors.getPreSkills(DEFAULT_SKILL_COLORS));
			waitToAttack(null);
			startegy = PropertiesUtil.getValueFromSkillsFile("SKILL_STRATEGY");
			switch("".equals(startegy)? GameConstant.NO_SKILL : startegy){
			    case GameConstant.NO_SKILL : {
					attackNPAAA();
			        break;
			    }
			    case GameConstant.GO_SECOD_SKILL_FOR_FIRST : {
					attackBAAForEvent(false, hases);
			        break;
			    }
			    case GameConstant.GO_ALL_SKILL_FOR_FIRST : {
					attackBAAForEvent(true, hases);
			        break;
			    }
			    default : {
			        break;
			    }
			}
			// 等待
			waitToAttack("1");
			eveValue = GameUtil.getColorEveValue(pointList);
		}
		// 第二回合
		// 2回合数字高亮点
		pointList = new ArrayList<>();
		pointList.add(new Point(906, 70));
		pointList.add(new Point(900, 66));
		pointList.add(new Point(899, 81));
		waitToAttack(null);
		LOGGER.info("第二回合开始：");
		eveValue = GameUtil.getColorEveValue(pointList);
		
		boolean hasSelect = false;
		int count = 0;
		PointColor pc;
		boolean ifSecondNP = EventFactors.ifSecondNP;
		while (eveValue > GameConstant.THRESHOLD&& battleRounds < MaxRounds) {
			battleRounds++;
			/*
			  连续使用技能组
			 */
			giveServantSkills(EventFactors.getPreSkills(DEFAULT_SKILL_COLORS));
			if (!hasSelect) {
				// 选择第2个怪物
				Point p_moster01 =POINT_INFO.getpMoster01();
				GameUtil.mouseMoveByPoint(p_moster01);
				GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
				hasSelect = true;
			}
			if (count==0) {
				//加回避
				giveClothSkills(EventFactors.getRound2ClothSkill());
			}
			pc = waitToAttack(null);
			if ("attack".equals(pc.getName())) {
				if (rounds==3 ) {
					if (ifSecondNP) {
						attackBAAForEvent(true, hases);
					}else {
						attackBAAForEvent(false, hases);
					}
				}else {
					attackBAAForEvent(true, hases);
				}
			}else {
				LOGGER.info("羁绊了，跳出来");
				break;
			}
			// 等待
			waitToAttack("3");
			eveValue = GameUtil.getColorEveValue(pointList);
			count++;
		}
		if (rounds==3) {
			// 3回合数字高亮点
			pointList = new ArrayList<>();
			pointList.add(new Point(900, 66));
			pointList.add(new Point(903, 74));
			pointList.add(new Point(907, 79));
			pointList.add(new Point(897, 82));
			// 第三回合
			// 等待
			waitToAttack(null);
			LOGGER.info("第三回合开始：");
			eveValue = GameUtil.getColorEveValue(pointList);
			hasSelect= false;
			while (eveValue > GameConstant.THRESHOLD && battleRounds < MaxRounds) {
				battleRounds++;
				waitToAttack("3");
				/*
				  连续使用技能组
				 */
				giveServantSkills(EventFactors.getPreSkills(DEFAULT_SKILL_COLORS));
				if (!hasSelect) {
					// 选择第个怪物
					Point monstor =EventFactors.getMonsterPoint();
					if (monstor!=null) {
						GameUtil.mouseMoveByPoint(monstor);
						GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
						waitToAttack(null);
						hasSelect = true;
					}
				}
				attackBAAForEvent(true, hases);
				// 宝具平A
				waitToAttack("3");
				if (checkFinish()) {
					LOGGER.info("羁绊了，跳出来");
					break;
				}
				eveValue = GameUtil.getColorEveValue(pointList);
			}
		}
	}
	/**
	 * 宝具平a攻击
	 * 
	 * @throws Exception 异常
	 */
	private void attackBAAForEvent(boolean goMainNP, boolean[] hases) throws Exception {
		Point pNp01 = POINT_INFO.getNpCheck();
		Point pNp02 = new Point((int)pNp01.getX()+ GameConstant.HEAD_SPACE, (int)pNp01.getY());
		Point pNp03 = new Point((int)pNp01.getX()+ GameConstant.HEAD_SPACE*2, (int)pNp01.getY());
		Color cNp01 = GameUtil.getScreenPixel(pNp01);
		Color cNp02 = GameUtil.getScreenPixel(pNp02);
		Color cNp03 = GameUtil.getScreenPixel(pNp03);
		GameUtil.delay(100);
		Color cNp01_Delay = GameUtil.getScreenPixel(pNp01);
		Color cNp02_Delay = GameUtil.getScreenPixel(pNp02);
		Color cNp03_Delay = GameUtil.getScreenPixel(pNp03);
		int npCount = 0;
		boolean hasNp01 = cNp01.getRGB() != cNp01_Delay.getRGB();
		boolean hasNp02 = cNp02.getRGB() != cNp02_Delay.getRGB();
		boolean hasNp03 = cNp03.getRGB() != cNp03_Delay.getRGB();
		boolean has = hasNp01
				|| hasNp02
				|| hasNp03;
		LOGGER.info("有宝具吗？  " + (has));
		if ( has ) {
			// 等待
			if (hasNp01) {
				if (!hases[0] && goMainNP) {
					// 战斗服技能
					battleSkillForP01();
					hases[0] = true;
					// 等待
					waitToAttack(null);
					GameUtil.delay(1000);
					/*
					  宝具技能组01
					 */
					giveServantSkills(EventFactors.getNPSkills01(DEFAULT_SKILL_COLORS));
				}
			}
			if (hasNp02) {
				/*
				  宝具技能组02
				 */
				giveServantSkills(EventFactors.getNPSkills02(DEFAULT_SKILL_COLORS));
			}	
			// 等待
			waitToAttack(null);
			// 蓝色圆板选择
            blueAttackSelect();
            // 开始点击卡片
			Map<String, List<CommonCard>> scMap;
			if (hasNp01 && goMainNP) {
				scMap = getWeakCommondCards(CardComparator.getRgbComparotor());
			}else {
				scMap = getWeakCommondCards(CardComparator.getBgrComparotor());
			}
			Point np_np01 = POINT_INFO.getNpNp();
			Point np_np02 = new Point((int) np_np01.getX()+GameConstant.NP_SPACE,(int) np_np01.getY());
			Point np_np03 = new Point((int) np_np01.getX()+GameConstant.NP_SPACE*2,(int) np_np01.getY());
			if (hasNp03) {
				// 宝具
				GameUtil.mouseMoveByPoint(np_np03);
				GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
				npCount++;
			}
			if (hasNp02) {
				// 宝具
				GameUtil.mouseMoveByPoint(np_np02);
				GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
				npCount++;
			}
			if (hasNp01 && goMainNP) {
				// 宝具
				GameUtil.mouseMoveByPoint(np_np01);
				GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
				npCount++;
			}
			List<CommonCard> trueList = scMap.get("trueList");
			List<CommonCard> falseList = scMap.get("falseList");
			int size = trueList.size();
			CommonCard commonCard;
			for (int i = 0; i < size; i++) {
				commonCard = trueList.get(i);
				GameUtil.mouseMoveByPoint(commonCard.getpLoc());
				GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
			}
			if (size < (3-npCount)) {
				int num = 2-trueList.size()+1;
				for (int j = 0; j < num; j++) {
					GameUtil.mouseMoveByPoint(falseList.get(j).getpLoc());
					GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
				}
			}
		} else {
			attackNPAAA();
		}
		checkExitCardSelect();
	}
	private void battleSkillForP01() throws Exception {
		// 战斗服选择
		Point p5 = new Point(1229, 359);// 颜色：24;60;107 Color c = new Color(24, 60, 107);
		GameUtil.mouseMoveByPoint(p5);
		GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
		// 选择技能2
		Point p8 = new Point(1029, 357);// 颜色：252;249;107 Color c = new Color(252, 249, 107);
		GameUtil.mouseMoveByPoint(p8);
		GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
		// 选择人物
		GameUtil.mouseMoveByPoint(POINT_INFO.getSkillPerson01());
		GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);

		// 等待
		waitToAttack(null);
		// 战斗服选择
		GameUtil.mouseMoveByPoint(p5);
		GameUtil.mousePressAndRelease(KeyEvent.BUTTON1_DOWN_MASK);
		// 选择技能3
		Point p9 = new Point(1115, 360);// 颜色：69;71;111 Color c = new Color(69, 71, 111);
		GameUtil.mouseMoveByPoint(p9);
		GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
		// 选择人物
		GameUtil.mouseMoveByPoint(POINT_INFO.getSkillPerson01());
		GameUtil.mousePressAndReleaseQuick(KeyEvent.BUTTON1_DOWN_MASK);
	}

	public static void main(String[] args) {
		// 1回合数字高亮点
		Point ps1 = new Point(903, 79);// 颜色：200;200;200 Color c = new Color(200, 200, 200);
		Point ps2 = new Point(903, 69);// 颜色：255;255;255 Color c = new Color(255, 255, 255);
		Point ps3 = new Point(904, 74);// 颜色：255;255;255 Color c = new Color(255, 255, 255);
		List<Point> pointList = new ArrayList<>();
		pointList.add(ps1);
		pointList.add(ps2);
		pointList.add(ps3);
		System.out.println(GameUtil.getColorEveValue(pointList));
	}
}
